package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.erbeandroid.petfinder.feature.component.databinding.ComponentTextFieldBinding

class TextFieldComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentTextFieldBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        // Get input text
        // val inputTextFilled = binding.filledTextField.editText?.text.toString()

        binding.filledTextField.editText?.doOnTextChanged { text, _, _, _ ->
            // Respond to input text change
            Log.d("TAG", "Text: $text")
        }

        // Get input text
        // val inputTextOutlined = binding.outlinedTextField.editText?.text.toString()

        binding.outlinedTextField.editText?.doOnTextChanged { text, start, before, count ->
            // Respond to input text change
            Log.d("TAG", "Text: $text")
        }

/*        // Set error text
        binding.filledTextField.error = context.resources.getString(R.string.error)

        // Clear error text
        binding.filledTextField.error = null*/

/*        textField.setEndIconOnClickListener {
            // Respond to end icon presses
        }

        textField.addOnEditTextAttachedListener {
            // If any specific changes should be done when the edit text is attached (and
            // thus when the trailing icon is added to it), set an
            // OnEditTextAttachedListener.

            // Example: The clear text icon's visibility behavior depends on whether the
            // EditText has input present. Therefore, an OnEditTextAttachedListener is set
            // so things like editText.getText() can be called.
        }

        textField.addOnEndIconChangedListener {
            // If any specific changes should be done if/when the endIconMode gets
            // changed, set an OnEndIconChangedListener.

            // Example: If the password toggle icon is set and a different EndIconMode
            // gets set, the TextInputLayout has to make sure that the edit text's
            // TransformationMethod is still PasswordTransformationMethod. Because of
            // that, an OnEndIconChangedListener is used.
        }*/
    }
}