package com.example.crewjetpackcompose.presentation.component

import android.annotation.SuppressLint
import androidx.annotation.LayoutRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@SuppressLint("ResourceType")
@Composable
fun TopBarDefault(
    title: String,
    @LayoutRes leftIcon: Int? = null,
    @LayoutRes rightIcon: Int? = null,
    onLeftIconClick: (() -> Unit)? = null,
    onRightIconClick: (() -> Unit)? = null,
    modifier: Modifier,
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leftIcon != null) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { onLeftIconClick?.invoke() },
                painter = painterResource(id = leftIcon),
                contentDescription = "@null"
            )
        }

        Text(text = title)

        if (rightIcon != null) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { onRightIconClick?.invoke() },
                painter = painterResource(id = rightIcon),
                contentDescription = "@null"
            )
        }
    }
}