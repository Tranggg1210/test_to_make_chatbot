"use client";
import ChatbotMessageItem from '@/components/atoms/chatbot-message-item'
import UserMessageItem from '@/components/atoms/user-message-item'
import ChatbotFooter from '@/components/organisms/chatbot-footer'
import { useAppSelector } from '../../../../helpers/hook'
import { IMessage } from '@/types/interface'

function Home() {
  const { listMessage } = useAppSelector((state) => state.chatReducer)
  return (
    <div className='max-w-lg mx-auto mt-4'>
      <div className='flex flex-col w-full my-6'>
        <ChatbotMessageItem message='Hi 👋 How can I help you?' />
        {listMessage &&
          listMessage.length > 0 &&
          listMessage.map((message: IMessage) => {
            return message?.isUser ? (
              <UserMessageItem key={message.id} message={message.content} />
            ) : (
              <ChatbotMessageItem key={message.id} message={message.content} />
            )
          })}
      </div>
      <ChatbotFooter />
    </div>
  )
}

export default Home
