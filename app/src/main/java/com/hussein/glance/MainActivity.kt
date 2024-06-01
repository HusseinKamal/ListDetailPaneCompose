@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.hussein.glance

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hussein.glance.ui.theme.GlanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlanceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListDetailLayout(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ListDetailLayout(modifier: Modifier=Modifier){
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane ={
                  LazyColumn(
                      modifier = Modifier.fillMaxSize(),
                      contentPadding = PaddingValues(16.dp)
                  ) {
                      items(100) { index ->
                          Text(
                              text = "Item $index",
                              modifier = Modifier
                                  .fillParentMaxWidth()
                                  .clickable {
                                      navigator.navigateTo(
                                          pane = ListDetailPaneScaffoldRole.Detail,
                                          content = "Item $index"
                                      )
                                  }
                                  .padding(16.dp),

                          )
                      }
                  }
        },
        detailPane ={
            val content = navigator.currentDestination?.content?.toString() ?: "Selected an Item"
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = content)
                Row {
                    AssistChip(
                        onClick = { navigator.navigateTo(pane = ListDetailPaneScaffoldRole.Extra, content = "Option 1") },
                        label = { Text(text = "Option 1") }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    AssistChip(
                        onClick = { navigator.navigateTo(pane = ListDetailPaneScaffoldRole.Extra, content = "Option 2") },
                        label = { Text(text = "Option 2") }
                    )
                }
            }
        },
        extraPane = {
            val content = navigator.currentDestination?.content?.toString() ?: "Selected an Item"
            Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.tertiary), contentAlignment = Alignment.Center){
                Text(text = content)
            }
        }
    )
    class MyReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            TODO("Not yet implemented")
        }
    }

}