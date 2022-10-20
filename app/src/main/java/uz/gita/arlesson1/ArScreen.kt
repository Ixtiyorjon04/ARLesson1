package uz.gita.arlesson1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class
ArScreen : Fragment(R.layout.screen_ar) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val arFragment = childFragmentManager.findFragmentById(R.id.ar_fragment) as ArFragment
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor: Anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            arFragment.arSceneView.scene.addChild(anchorNode)

            val node = TransformableNode(arFragment.transformationSystem)
            node.setParent(anchorNode)

            ModelRenderable.builder().setSource(requireContext(), R.raw.notebook).build().thenAccept {
                node.renderable = it
                node.select()
            }
//            node.scaleController.maxScale
            node.scaleController.minScale = 0.05f
//

        }
    }
}