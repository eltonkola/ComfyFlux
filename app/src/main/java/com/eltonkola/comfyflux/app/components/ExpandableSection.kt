package com.eltonkola.comfyflux.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.ArrowDown
import com.eltonkola.comfyflux.ui.theme.ikona.Package
import com.eltonkola.comfyflux.ui.theme.ikona.WorkflowIc

@Composable
fun ExpandableSection(
    title: String,
    expanded: Boolean,
    onExpand:() -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Ikona.Package,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
                )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = title,
                modifier = Modifier.weight(1f)
                )
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                imageVector = Ikona.ArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onExpand() }
                    .rotate(if (expanded) 180f else 0f)
            )
        }
        if(expanded){
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                content()
            }
        }
    }

}

@Preview
@Composable
fun ExpandableSectionPreview(){
    Column {

        ExpandableSection(
            expanded = true,
            title = "Local",
            onExpand = {}
        ){
            Spacer(modifier = Modifier.size(80.dp))
            Text(text = "something")
            Spacer(modifier = Modifier.size(80.dp))
        }
        Spacer(modifier = Modifier.size(80.dp))

        ExpandableSection(
            expanded = false,
            title = "Local",
            onExpand = {}
        ){
            Spacer(modifier = Modifier.size(80.dp))
            Text(text = "something")
            Spacer(modifier = Modifier.size(80.dp))
        }


    }

}