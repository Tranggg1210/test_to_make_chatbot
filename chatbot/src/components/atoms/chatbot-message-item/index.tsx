import Image from 'next/image'
import Chatbot from '@/assets/images/chatbot.png';

function ChatbotMessageItem({ message }: { message: string }) {
  return (
    <div className='flex items-start justify-start mb-4'>
      <Image
        src={Chatbot}
        alt='Bot'
        className='w-8 h-8 mx-2 -mt-3'
        width={36}
        height={36}
      />
      <div className='bg-gray-200 text-black py-2 px-4 rounded-lg rounded-tl-none'>{message}</div>
    </div>
  )
}

export default ChatbotMessageItem
