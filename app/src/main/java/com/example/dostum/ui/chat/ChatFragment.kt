package com.example.dostum.ui.chat

class ChatFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvChatList)

        val chatList = listOf(
            ChatItem(
                name = getString(R.string.translator),
                icon = R.drawable.ic_translate,
                isTranslator = true
            ),
            ChatItem(
                name = getString(R.string.chat_assistant),
                icon = R.drawable.ic_chat,
                isTranslator = false
            )
        )

        recyclerView.adapter = ChatAdapter(chatList) { chatItem ->
            val intent = Intent(context, ChatDetailActivity::class.java).apply {
                putExtra("is_translator", chatItem.isTranslator)
            }
            startActivity(intent)
        }

        return view
    }
}