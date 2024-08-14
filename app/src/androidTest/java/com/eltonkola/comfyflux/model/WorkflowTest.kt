package com.eltonkola.comfyflux.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eltonkola.comfyflux.R
import com.eltonkola.comfyflux.data.model.Meta
import com.eltonkola.comfyflux.data.model.Node
import com.eltonkola.comfyflux.data.model.SizeInputs
import com.eltonkola.comfyflux.data.model.Workflow
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import java.io.BufferedReader
import java.io.InputStreamReader

@RunWith(AndroidJUnit4::class)
class WorkflowTest {

    @Test
    fun testWorkflowParsing() {
        // Get the application context
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Read the JSON file from the raw resource
        val inputStream = context.resources.openRawResource(R.raw.sdxl_lighting_4steps)
        val jsonString = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }

        // Parse the JSON into a Workflow object
        val workflow = Json.decodeFromString<Workflow>(jsonString)

        // Test parsing
        assertNotNull(workflow)
        assertTrue(workflow.containsKey("5"))

        // Test getting a typed node
        val node5 = workflow.getTypedNode<SizeInputs>("5")
        assertNotNull(node5)
        assertEquals(1024, node5?.inputs?.width)
        assertEquals(1024, node5?.inputs?.height)
        assertEquals(1, node5?.inputs?.batchSize)

        // Update the node
        workflow.updateTypedNode<SizeInputs>("5") { node ->
            node.copy(inputs = node.inputs.copy(width = 2048, height = 2048, batchSize = 2))
        }

        // Verify the update
        val updatedNode5 = workflow.getTypedNode<SizeInputs>("5")
        assertEquals(2048, updatedNode5?.inputs?.width)
        assertEquals(2048, updatedNode5?.inputs?.height)
        assertEquals(2, updatedNode5?.inputs?.batchSize)

        // Test adding a new node
        workflow["12"] = Node(
            inputs = Json.parseToJsonElement("""{"new_param": "value"}"""),
            classType = "NewNodeType",
            meta = Meta("New Node")
        )

        // Verify the new node
        assertTrue(workflow.containsKey("12"))
        val newNode = workflow["12"]
        assertEquals("NewNodeType", newNode?.classType)
        assertEquals("New Node", newNode?.meta?.title)

        // Convert back to JSON and verify
        val modifiedJsonString = workflow.toJson()
        assertTrue(modifiedJsonString.contains("\"12\":"))
        assertTrue(modifiedJsonString.contains("\"new_param\":\"value\""))
    }
}