package com.hellcorp.composeprjctxmpl

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.hellcorp.composeprjctxmpl.ui.theme.ComposePrjctXmplTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contactList = listOf(
            Contact(
                name = "Jeerom",
                fathersName = "Jake",
                familyName = "Jeebs",
                isFavorite = true,
                phone = "+1-254-233-22-43",
                address = "700 Los Esteros Rd, San Jose, CA 95134, USA",
                email = "had@mail.com"
            ),
            Contact(
                name = "Magnus",
                fathersName = "Henrik",
                familyName = "Carlsen",
                isFavorite = true,
                phone = "+1-254-233-22-43",
                address = "Myrholtet, 5142 Bergen, Norway",
                email = "had@mail.com"
            ),
            Contact(
                name = "Mike",
                familyName = "Sheeer",
                imageRes = R.drawable.mock_avatar,
                phone = "+1-254-233-22-43",
                address = "6 Wardlaw Pl, Edinburgh EH11 1UB, GB",
            )
        )
        setContent {
            ComposePrjctXmplTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        contactList.forEach {
                            ContactDetails(contact = it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    val str = StringBuilder()
    str.append(contact.name[0])
    str.append(contact.familyName[0])
    val initials = str.toString().uppercase()
    str.clear()
    str.append(contact.name)
    contact.fathersName?.let {
        str.append(" $it")
    }
    val namefathersName = str.toString()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (contact.imageRes == null) {
                    Icon(
                        painter = painterResource(id = R.drawable.circle),
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                } else {
                    Image(
                        painter = painterResource(id = contact.imageRes),
                        contentDescription = null
                    )
                }

                if (contact.imageRes == null) {
                    Text(
                        text = initials,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }


            Text(
                text = namefathersName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = contact.familyName,
                    style = MaterialTheme.typography.titleLarge,
                )
                if (contact.isFavorite) {
                    Image(
                        painter = painterResource(id = android.R.drawable.star_big_on),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InfoRow(resource = R.string.phone, data = contact.phone)
                InfoRow(resource = R.string.address, data = contact.address)
                InfoRow(resource = R.string.email, data = contact.email)
            }
        }

    }
}

@Composable
fun InfoRow(resource: Int, data: String?) {
    data?.let {
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${stringResource(id = resource)}: ",
                    fontStyle = FontStyle.Italic
                )
            }
            Row(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.weight(2f),
                    text = data,
                    style = MaterialTheme.typography.titleMedium
                )
            }

        }
    }
}

@Preview(
    device = "spec:width=411dp,height=891dp",
    backgroundColor = 0xFFFFFEFE,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE, showSystemUi = true, showBackground = true
)
@Composable
fun GreetingPreview() {
    ComposePrjctXmplTheme {
        ContactDetails(
            contact = Contact(
                name = "Jeerom",
                fathersName = "Jake",
                familyName = "Jeebs",
                isFavorite = true,
                imageRes = R.drawable.mock_avatar,
                phone = "+1-254-233-22-43",
                address = "700 Los Esteros Rd, San Jose, CA 95134, USA",
                email = "had@mail.com"
            )
        )
    }
}