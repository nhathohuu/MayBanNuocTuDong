package com.example.bluetooth.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bluetooth.R
import com.example.bluetooth.presentation.BluetoothUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceScreen(
    state: BluetoothUiState,
    onProductSelect: (String) -> Unit
) {
    val banners = listOf(
        R.drawable.ads,
        R.drawable.ads_coca,
        R.drawable.ads_water
    )

    Scaffold(
        containerColor = Color(0xFFF1F2F6),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "CHÀO MỪNG ĐẾN VỚI",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                        Text(
                            text = "Máy Bán Nước Tự Động",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    }
                },
                // SỬA LỖI LỆCH XUỐNG: Thiết lập WindowInsets về 0 để không bị đẩy bởi Status Bar
                windowInsets = WindowInsets(0, 0, 0, 0),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF0984E3), Color(0xFF74C0FC))
                        )
                    )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Banner quảng cáo đã sửa lỗi to hơn và tràn viền
            AutoSlidingCarousel(banners = banners)

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                if (state.isConnected) Color(0xFF4CAF50) else Color(0xFFFF9800),
                                CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = if (state.isConnected) "Hệ thống đang sẵn sàng" else "Đang tìm kiếm thiết bị...",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2D3436)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Danh mục sản phẩm",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF2D3436),
                modifier = Modifier.align(Alignment.Start)
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp), 
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DrinkItem(
                    modifier = Modifier.weight(1f),
                    name = "Coca Cola",
                    price = "10.000đ",
                    imageRes = R.drawable.coca,
                    color = Color(0xFFE84118),
                    isEnabled = state.isConnected,
                    onSelect = { onProductSelect("COCA") }
                )

                DrinkItem(
                    modifier = Modifier.weight(1f),
                    name = "Nước lọc",
                    price = "10.000đ",
                    imageRes = R.drawable.nuocloc,
                    color = Color(0xFF00A8FF),
                    isEnabled = state.isConnected,
                    onSelect = { onProductSelect("WATER") }
                )
            }

            if (state.errorMessage != null) {
                Text(
                    text = state.errorMessage,
                    color = Color.Red,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 4000,
    banners: List<Int>
) {
    val pagerState = rememberPagerState(pageCount = { banners.size })

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(autoSlideDuration)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % banners.size,
                animationSpec = tween(800)
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .shadow(15.dp, RoundedCornerShape(28.dp), ambientColor = Color(0xFF0984E3).copy(alpha = 0.3f))
            .background(Color.White, RoundedCornerShape(28.dp))
            .clip(RoundedCornerShape(28.dp))
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = banners[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Lấp đầy khung banner
                modifier = Modifier.fillMaxSize() // Xóa padding để ảnh to nhất có thể
            )
        }
        
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            repeat(banners.size) { index ->
                val isSelected = pagerState.currentPage == index
                val width by animateDpAsState(
                    targetValue = if (isSelected) 24.dp else 8.dp,
                    animationSpec = tween(300),
                    label = "width"
                )
                
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .width(width)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) Color(0xFF0984E3) 
                            else Color(0xFF0984E3).copy(alpha = 0.2f)
                        )
                )
            }
        }
    }
}

@Composable
fun DrinkItem(
    modifier: Modifier = Modifier,
    name: String,
    price: String,
    imageRes: Int,
    color: Color,
    isEnabled: Boolean,
    onSelect: () -> Unit
) {
    Card(
        onClick = onSelect,
        enabled = isEnabled,
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(color.copy(alpha = 0.05f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = name,
                    modifier = Modifier.size(100.dp).padding(8.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = price, color = color, fontWeight = FontWeight.Black)
        }
    }
}
