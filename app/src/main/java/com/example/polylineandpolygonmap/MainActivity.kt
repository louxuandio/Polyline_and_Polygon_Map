package com.example.polylineandpolygonmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polylineandpolygonmap.ui.theme.PolylineAndPolygonMapTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PolylineAndPolygonMapTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PolyMap()
                }
            }
        }
    }
    /*
    override fun onMapReady(googleMap: GoogleMap) {
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(42.439560,-71.334251))
            .zoom(6f)
            .tilt(0f)
            .bearing(0f)
            .build()

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
     */
}

@Composable
fun PolyMap(){
    val context = LocalContext.current

    // I first tried to do with a kml file, but at last I decided to draw the polyline with a list of LatLng.
    // I drew the trail on a map, and export it as kml file. I sent the kml to ChatGPT to give me the list.
    val polylinePoints = remember {
        listOf(
            LatLng(42.439543, -71.3342915),
            LatLng(42.439638, -71.3346456),
            LatLng(42.4399785, -71.3349782),
            LatLng(42.4402635, -71.3359974),
            LatLng(42.4405565, -71.3377784),
            LatLng(42.4404456, -71.3383899),
            LatLng(42.4405011, -71.3390873),
            LatLng(42.4404298, -71.3404821),
            LatLng(42.4403902, -71.3409971),
            LatLng(42.4404456, -71.3413297),
            LatLng(42.4408811, -71.341673),
            LatLng(42.4411661, -71.3418339),
            LatLng(42.4414432, -71.3420485),
            LatLng(42.441372, -71.3422952),
            LatLng(42.4408811, -71.3423918),
            LatLng(42.440794, -71.3425742),
            LatLng(42.4407465, -71.3430141),
            LatLng(42.4403585, -71.3433467),
            LatLng(42.4399547, -71.3433896),
            LatLng(42.4397172, -71.3434325),
            LatLng(42.4392817, -71.3438295),
            LatLng(42.439163, -71.3441728),
            LatLng(42.4391946, -71.3447736),
            LatLng(42.4390759, -71.3450204),
            LatLng(42.4387987, -71.3450526),
            LatLng(42.4385612, -71.3449238),
            LatLng(42.4384108, -71.3446341),
            LatLng(42.4382524, -71.3443445),
            LatLng(42.438094, -71.3441299),
            LatLng(42.4379674, -71.3438831),
            LatLng(42.4377219, -71.3438509),
            LatLng(42.4374368, -71.3439904),
            LatLng(42.437136, -71.3437973),
            LatLng(42.4368667, -71.3434754),
            LatLng(42.4369855, -71.342778),
            LatLng(42.4370328, -71.342405),
            LatLng(42.4372327, -71.3420912),
            LatLng(42.4373574, -71.3418713),
            LatLng(42.4374029, -71.3412302),
            LatLng(42.4373534, -71.3410049),
            LatLng(42.4371951, -71.3408306),
            LatLng(42.4370374, -71.3406932),
            LatLng(42.4369879, -71.3404464),
            LatLng(42.4369404, -71.3403687),
            LatLng(42.4369602, -71.3402399),
            LatLng(42.4370631, -71.3401246),
            LatLng(42.4373125, -71.3400334),
            LatLng(42.4376491, -71.3396552),
            LatLng(42.437847, -71.3391992),
            LatLng(42.4380548, -71.3390383),
            LatLng(42.4381815, -71.3387137),
            LatLng(42.4382174, -71.3379891),
            LatLng(42.4381659, -71.3374902),
            LatLng(42.4381105, -71.3370128),
            LatLng(42.4379956, -71.3364656),
            LatLng(42.4377383, -71.3362725),
            LatLng(42.4376948, -71.3358916),
            LatLng(42.4379996, -71.3350977),
            LatLng(42.4385737, -71.3347275),
            LatLng(42.4392031, -71.3344861),
            LatLng(42.439543, -71.3342915)
        )
    }
    val boundsBuilder = remember {
        LatLngBounds.builder().apply {
            polylinePoints.forEach { include(it) }
        }
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(polylinePoints.first(), 6f)
    }
    val reservationPolygon = listOf(
        LatLng(42.445269, -71.350697),
        LatLng(42.444217, -71.340038),
        LatLng(42.439909, -71.334013),
        LatLng(42.438709, -71.333515),
        LatLng(42.436378, -71.334906),
        LatLng(42.434519, -71.335898),
        LatLng(42.433342, -71.335785),
        LatLng(42.432171, -71.334422),
        LatLng(42.431552, -71.334408),
        LatLng(42.428259, -71.336386),
    )

    var polygonColor by remember { mutableStateOf(Color(0x3333CC33)) }
    var polylineColor by remember { mutableStateOf(Color.Blue) }
    var lineWidth by remember { mutableStateOf(5f) }

    var showTrailInfo by remember { mutableStateOf(false) }
    var showParkInfo by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        val bounds = boundsBuilder.build()
        cameraPositionState.move(
            CameraUpdateFactory.newLatLngBounds(bounds, 100)
        )
    }

    Column (
        modifier = Modifier.padding(8.dp)
    ){
        // choose color for polygon
        Text("Polygon Color:", style = TextStyle(fontSize = 24.sp) )
        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ){
            Button(onClick = { polygonColor = Color(0x3333CC33) }) { Text("Green") }
            Button(onClick = { polygonColor = Color(0x330000FF) }) { Text("Blue") }
            Button(onClick = { polygonColor = Color(0x33FFFF00) }) { Text("Yellow") }
        }
        Text("Polyline Color:", style = TextStyle(fontSize = 24.sp) )
        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ){
            Button(onClick = { polylineColor = Color.Green }) { Text("Green") }
            Button(onClick = { polylineColor = Color.Blue }) { Text("Blue") }
            Button(onClick = { polylineColor = Color.Yellow }) { Text("Yellow") }
        }
        Text("Polyline Width:", style = TextStyle(fontSize = 24.sp))
        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ){
            Slider(
                value = lineWidth,
                onValueChange = { lineWidth = it },
                valueRange = 1f..20f,
                modifier = Modifier.weight(1f)
            )
        }
        Box{
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Polyline(
                    points = polylinePoints, // Pass the list of LatLng coordinates
                    color = polylineColor, // Set the polyline color
                    width = lineWidth, // Set the polyline width
                    pattern = listOf(Dash(20f), Gap(10f))
                )

                Polygon(
                    points = reservationPolygon,
                    fillColor = polygonColor,
                    strokeColor = Color.Black,
                    strokeWidth = 3f
                )
                Marker(
                    state = MarkerState(position = polylinePoints.first()),
                    title = "Walden Pond Trail",
                    snippet = "Walden Pond Trail",
                    onClick = {
                        showTrailInfo = true
                        true
                    }
                )

                Marker(
                    state = MarkerState(position = LatLng(42.440238,-71.344354)),
                    title = "Walden Pond State Reservation",
                    snippet = "Walden Pond State Reservation",
                    onClick = {
                        showParkInfo = true
                        true
                    }
                )
            }
        }
    }

    //I asked ChatGPT how to show more details when clicking on a marker, and ChatGPT taught me what is and how to do AlertDialog.
    if (showTrailInfo){
        AlertDialog(
            onDismissRequest = {showTrailInfo = false},
            title = { Text("Walden Pond Trail") },
            text = {
                Text("Length: 1.8 miles\nEstimated time: 34 minutes\nDifficulty: Easy\nSurface: gravel, paved\nScenery: Forest, lakeshore")
            },
            confirmButton = {
                TextButton(onClick = { showTrailInfo = false }) {
                    Text("Close")
                }
            }
        )
    }
    if (showParkInfo){
        AlertDialog(
            onDismissRequest = {showParkInfo = false},
            title = { Text("Walden Pond State Reservation") },
            text = {
                Text("The Walden Pond State Reservation offers hiking, swimming, and historical landmarks such as Thoreau's cabin site. The area is protected for conservation and education.")
            },
            confirmButton = {
                TextButton(onClick = { showParkInfo = false }) {
                    Text("Close")
                }
            }
        )
    }
}
