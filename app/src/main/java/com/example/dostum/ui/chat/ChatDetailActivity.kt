package com.example.dostum.ui.chat

class ChatDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatDetailBinding
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isTranslator = intent.getBooleanExtra("is_translator", false)

        binding.btnSend.setOnClickListener {
            val inputText = binding.etInput.text.toString()
            if (inputText.isNotEmpty()) {
                processMessage(inputText, isTranslator)
                binding.etInput.text.clear()
            }
        }
    }

    private fun processMessage(text: String, isTranslator: Boolean) {
        val userMessage = Message(text, isUser = true)
        messages.add(userMessage)

        val botResponse = if (isTranslator) {
            translateText(text) // 调用翻译API
        } else {
            text // 直接返回原文本
        }

        messages.add(Message(botResponse, isUser = false))
        updateChatList()
    }

    private fun updateChatList() {
        binding.rvMessages.adapter?.notifyDataSetChanged()
        binding.rvMessages.scrollToPosition(messages.size - 1)
    }
}