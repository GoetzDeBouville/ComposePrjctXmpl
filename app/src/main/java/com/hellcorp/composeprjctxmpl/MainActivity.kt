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
import androidx.compose.runtime.remember
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
fun ContactDetails(modifier: Modifier = Modifier, contact: Contact) {
    val initials = remember { buildInitials(contact.name, contact.familyName) }
    val fathersName = remember { buildNameFathersName(contact.name, contact.fathersName) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (contact.imageRes == null) {
                    Icon(
                        painter = painterResource(id = R.drawable.circle),
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                    Text(
                        text = initials,
                        style = MaterialTheme.typography.titleMedium
                    )
                } else {
                    Image(
                        painter = painterResource(id = contact.imageRes),
                        contentDescription = null
                    )
                }
            }

            Text(
                text = fathersName,
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
                        modifier = modifier.padding(start = 8.dp)
                    )
                }
            }

            Column(
                modifier = modifier.padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InfoRow(resource = R.string.phone, data = contact.phone)
                InfoRow(resource = R.string.address, data = contact.address)
                InfoRow(resource = R.string.email, data = contact.email)
            }
        }

    }
}

private fun buildInitials(name: String, familyName: String) =
    (name.first().toString() + familyName.first().toString()).uppercase()


private fun buildNameFathersName(name: String, fathersName: String?): String {
    val str = StringBuilder(name)
    fathersName?.let { str.append(" $it") }
    return str.toString()
}

@Composable
fun InfoRow(modifier: Modifier = Modifier, resource: Int, data: String?) {
    data?.let {
        Row(
            modifier = modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = modifier.weight(1f)) {
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = "${stringResource(id = resource)}: ",
                    fontStyle = FontStyle.Italic
                )
            }
            Row(modifier = modifier.weight(1f)) {
                Text(
                    modifier = modifier.weight(2f),
                    text = data,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(
    device = "spec:width=400dp,height=600dp",
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
                phone = "+1-254-233-22-43",
                address = "700 Los Esteros Rd, San Jose, CA 95134, USA",
                email = "had@mail.com"
            )
        )
    }
}

@Preview(
    device = "spec:width=400dp,height=600dp",
    backgroundColor = 0xFFFFFEFE,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE, showSystemUi = true, showBackground = true
)
@Composable
fun NoImagePreview() {
    ComposePrjctXmplTheme {
        ContactDetails(
            contact = Contact(
                name = "Jeerom",
                familyName = "Jeebs",
                imageRes = R.drawable.mock_avatar,
                phone = "---",
                address = "700 Los Esteros Rd, San Jose, CA 95134, USA",
            )
        )
    }
}