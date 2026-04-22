#include <BluetoothSerial.h>
#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif

BluetoothSerial BT;
char cmd;

void setup() {
  Serial.begin(115200);
  delay(1000);

  Serial.println("--- ĐANG KHỞI TẠO HỆ THỐNG ---");

  if (!BT.begin("ESP32-Bluetooth")) {
    Serial.println("LỖI: Không thể khởi động Bluetooth!");
    while (1); 
  }

  Serial.println("Bluetooth đã sẵn sàng!");
}

void loop() {
  // 1. Đọc dữ liệu từ Bluetooth và in ra Serial Monitor
  if (BT.available()) {
    cmd = BT.read();
    Serial.print("Đã nhận từ điện thoại: ");
    Serial.println(cmd);
  }
  
  delay(20); 
}