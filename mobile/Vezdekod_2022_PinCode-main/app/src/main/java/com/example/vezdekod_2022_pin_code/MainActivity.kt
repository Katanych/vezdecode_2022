package com.example.vezdekod_2022_pin_code

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.vezdekod_2022_pin_code.databinding.ActivityMainBinding
import com.example.vezdekod_2022_pin_code.viewModel.PinViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var state = ""

        val viewModel: PinViewModel by viewModels()
        var countNumbers = 0
        val arrayPin = arrayOf(0, 0, 0, 0)
        viewModel.getPin()
        viewModel.pin.observe(this) { pin ->
            if (pin == "") {
                state = STATE_CREATE
                ivDeletePin.visibility = View.GONE
                tvEnterPin.text = getString(R.string.sign_up_PIN)
                countNumbers = 0
            } else {
                state = STATE_ENTER_PIN
                ivDeletePin.visibility = View.VISIBLE
                tvEnterPin.text = getString(R.string.enter_PIN)
                countNumbers = 0
            }
        }

        binding.apply {
            tvOne.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(1, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            tvTwo.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(2, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            tvTree.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(3, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            tvFour.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(4, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            tvFive.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(5, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            tvSix.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(6, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            tvSeven.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(7, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            tvEight.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(8, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            tvNine.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(9, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            tvZero.setOnClickListener {
                if (countNumbers != 4) {
                    countNumbers++
                    checkFullEllipse(countNumbers, binding)
                    enterPin(0, arrayPin, countNumbers, viewModel, state, binding)
                }
            }
            ivDelete.setOnClickListener {
                if (countNumbers != 0) {
                    countNumbers--
                    checkEmptyEllipse(countNumbers, binding)
                }
            }
            ivDeletePin.setOnClickListener {
                viewModel.deletePin()
                resetEllipse(binding)
            }
        }
    }

    private fun checkFullEllipse(countNumbers: Int, binding: ActivityMainBinding) {
        when (countNumbers) {
            1 -> binding.ellipse1.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_full))
            2 -> binding.ellipse2.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_full))
            3 -> binding.ellipse3.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_full))
            4 -> binding.ellipse4.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_full))
        }
    }

    private fun checkEmptyEllipse(countNumbers: Int, binding: ActivityMainBinding) {
        when (countNumbers) {
            0 -> binding.ellipse1.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_empty))
            1 -> binding.ellipse2.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_empty))
            2 -> binding.ellipse3.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_empty))
            3 -> binding.ellipse4.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_empty))
        }
    }

    private fun enterPin(number: Int, array: Array<Int>, countNumbers: Int, viewModel: PinViewModel, state: String, binding: ActivityMainBinding) {
        var pin = ""
        array[countNumbers - 1] = number
        if (countNumbers == 4) {
            pin = array.joinToString()
            if (state == STATE_CREATE) {
                viewModel.createPin(pin)
                Toast.makeText(applicationContext, getString(R.string.pin_created), Toast.LENGTH_LONG).show()
                resetEllipse(binding)
            } else
                viewModel.setPin(pin) {
                    if (!it) {
                        Toast.makeText(applicationContext, getString(R.string.enter_pin_error), Toast.LENGTH_LONG).show()
                        resetEllipse(binding)
                    }
                    else {
                        Toast.makeText(applicationContext, getString(R.string.enter_pin_ok), Toast.LENGTH_LONG).show()
                        resetEllipse(binding)
                    }
                }
        }
    }

    private fun resetEllipse(binding: ActivityMainBinding) {
        binding.ellipse1.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_empty))
        binding.ellipse2.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_empty))
        binding.ellipse3.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_empty))
        binding.ellipse4.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_ellipse_empty))
    }

    companion object {
        private const val STATE_CREATE = "create"
        private const val STATE_ENTER_PIN = "enter"
    }
}