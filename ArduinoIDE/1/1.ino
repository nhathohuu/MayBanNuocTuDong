#include "BluetoothSerial.h"
#include <ESP32Servo.h>

BluetoothSerial SerialBT;

Servo servoCoca;
Servo servoWater;

const int pinCoca = 18;  
const int pinWater = 19;

const int sensorPin = 4;

String device_name = "ESP32-Bluetooth";
bool isProcessing = false;

int stop = 90;
int run = 120;

void setup() {
  Serial.begin(115200);
  pinMode(sensorPin, INPUT_PULLUP); 

  servoCoca.write(stop); 
  servoWater.write(stop);

  delay(500);
  // Thiết lập chân cho 2 servo
  servoCoca.attach(pinCoca, 500, 2400);
  servoWater.attach(pinWater, 500, 2400);
  
  // Đưa cả 2 về vị trí 0 độ (vị trí đóng)


  if (!SerialBT.begin(device_name)) {
    Serial.println("Lỗi khởi động Bluetooth!");
    while (1);
  }
  
  Serial.println("--- MÁY BÁN NƯỚC: HỆ THỐNG SẴN SÀNG ---");
}

void runUntilDetected(Servo &servo) {
  servo.write(run);

  unsigned long start = millis();

  while (true) {
    if (digitalRead(sensorPin) == LOW) {
      Serial.println("Nước được đẩy xuống thành công");
      break;
    }

    if (millis() - start > 5000 ) {
      Serial.println("Nước đẩy xuống không thành công ");
    }
  }
}

void loop() {
  if (SerialBT.available()) {
    String command = "";
    while (SerialBT.available()) {
      char c = SerialBT.read();
      if (c == '\n') break;
      command += c;
    }
    command.trim();

    if (command.length() > 0 && !isProcessing) {
      Serial.println("Lệnh nhận được: " + command);

      if (command == "COCA") {
        isProcessing = true;
        Serial.println("=> Đang nhả COCA...");
        
        servoCoca.write(run); // Sử dụng biến run của bạn
        delay(2000);         
        servoCoca.write(stop); // Sử dụng biến stop
        
        SerialBT.println("STATUS|COMPLETED");
        // Quan trọng: Xóa sạch dữ liệu thừa còn trong buffer
        while(SerialBT.available()) SerialBT.read(); 
        isProcessing = false;
      } 
      else if (command == "WATER") {
        isProcessing = true;
        Serial.println("=> Đang nhả NƯỚC LỌC...");
        
        servoWater.write(run); 
        delay(2000);
        servoWater.write(stop);
        
        SerialBT.println("STATUS|COMPLETED");
        while(SerialBT.available()) SerialBT.read();
        isProcessing = false;   
      }
      // Thêm lệnh RESET để app có thể yêu cầu ESP32 về trạng thái chờ
      else if (command == "RESET") {
        isProcessing = false;
        servoCoca.write(stop);
        servoWater.write(stop);
      }
    }
  }
  delay(10);

  
}