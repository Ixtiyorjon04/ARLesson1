package uz.gita.arlesson1

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.permissionx.guolindev.PermissionX

class PermissionScreen : Fragment(R.layout.screen_permission) {
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val button: Button = view.findViewById(R.id.btn_permission)
        button.setOnClickListener {
            PermissionX.init(requireActivity())
                .permissions( Manifest.permission.CAMERA)
                .request { allGranted, _, deniedList ->
                    if (allGranted) {
                        navController.navigate(R.id.arScreen)
                    } else {
                        Toast.makeText(requireActivity(), "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}