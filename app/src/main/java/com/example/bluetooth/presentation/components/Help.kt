package com.example.bluetooth.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bluetooth.presentation.BluetoothUiState

@Preview(showBackground = true)
@Composable
fun HelpScreenPreview() {
    HelpScreen()
}


@Composable
fun HelpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Hướng dẫn mua hàng",
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF2D3436),
            modifier = Modifier.align(Alignment.Start)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        HelpStepItem(
            step = "1",
            title = "Chọn đồ uống",
            description = "Chạm trực tiếp vào hình ảnh Coca hoặc Nước lọc trên màn hình chính.",
            icon = Icons.Default.ShoppingCart
        )
        
        HelpStepItem(
            step = "2",
            title = "Thanh toán QR",
            description = "Dùng app Ngân hàng quét mã QR thanh toán hiển thị trên máy.",
            icon = Icons.Default.Info
        )
        
        HelpStepItem(
            step = "3",
            title = "Nhận sản phẩm",
            description = "Hệ thống sẽ tự động đẩy nước ra sau khi giao dịch thành công.",
            icon = Icons.Default.CheckCircle
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Hỗ trợ khách hàng",
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF2D3436),
            modifier = Modifier.align(Alignment.Start)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        ContactCard(
            icon = Icons.Default.Phone,
            label = "Hotline hỗ trợ kỹ thuật",
            value = "0000000000"
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        ContactCard(
            icon = Icons.Default.Email,
            label = "Góp ý dịch vụ",
            value = "abc@gmail.com"
        )
        
        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
fun HelpStepItem(step: String, title: String, description: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF0984E3).copy(alpha = 0.1f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = Color(0xFF0984E3))
            }
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column {
            Text(
                text = "Bước $step: $title",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF2D3436)
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun ContactCard(icon: ImageVector, label: String, value: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = Color(0xFF0984E3))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = label, fontSize = 12.sp, color = Color.Gray)
                Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2D3436))
            }
        }
    }
}
