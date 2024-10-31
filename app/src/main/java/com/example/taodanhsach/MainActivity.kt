package com.example.taodanhsach
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các thành phần giao diện
        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listView = findViewById(R.id.listView)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        // Ẩn TextView lỗi
        textViewError.visibility = TextView.GONE
        textViewError.text = ""

        // Lấy giá trị n từ EditText
        val input = editTextNumber.text.toString()
        val n = input.toIntOrNull()

        if (n == null || n < 0) {
            // Hiển thị lỗi nếu n không hợp lệ
            textViewError.text = "Vui lòng nhập một số nguyên dương"
            textViewError.visibility = TextView.VISIBLE
            return
        }

        // Xác định loại số dựa trên RadioButton đã chọn
        val selectedId = radioGroup.checkedRadioButtonId
        val numbers = when (selectedId) {
            R.id.radioEven -> generateEvenNumbers(n)
            R.id.radioOdd -> generateOddNumbers(n)
            R.id.radioSquare -> generateSquareNumbers(n)
            else -> emptyList()
        }

        // Hiển thị danh sách số trong ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        listView.adapter = adapter
    }

    // Hàm tạo danh sách số chẵn
    private fun generateEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    // Hàm tạo danh sách số lẻ
    private fun generateOddNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 != 0 }
    }

    // Hàm tạo danh sách số chính phương
    private fun generateSquareNumbers(n: Int): List<Int> {
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        return squares
    }
}
