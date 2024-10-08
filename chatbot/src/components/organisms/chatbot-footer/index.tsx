'use client'

import React, { useState } from 'react'
import InputComponent from '@/components/atoms/input'
import Send from '@/assets/images/send.png'
import Image from 'next/image'
import axios from 'axios'
import { toast } from 'react-toastify'
import { useAppDispatch } from '../../../../helpers/hook'
import { addMessage } from '@/reducers/redux-utils/chat'

function ChatbotFooter() {
  const [sendInput, setSendInput] = useState<string>('')
  const [disabled, setDisabled] = useState(false)
  const dispatch = useAppDispatch()

  const handleSend = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault()

    try {
      setDisabled(true)
      if (sendInput.trim()) {
        dispatch(
          addMessage({
            id: 0,
            content: sendInput.trim() || 'Lỗi 🥹',
            isUser: true,
          }),
        )
        const res = await axios.post(
          'https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyDPwsWtk_tcdP6vNQZV0GH4NeaTUi7SsZY',
          {
            contents: [
              {
                parts: [
                  {
                    text: sendInput.trim(),
                  },
                ],
              },
            ],
          },
        )
        setSendInput('')
        if (res) {
          dispatch(
            addMessage({
              id: 0,
              content:
                res?.data?.candidates?.[0]?.content?.parts?.[0]?.text ||
                'Lỗi, không thể kết nối 🥹',
              isUser: false,
            }),
          )
        }
      } else {
        toast.warning('Vui lòng nhập tin nhắn của bạn!!!')
        return
      }
    } catch (error) {
      console.log(error)
      toast.error('Lỗi, không thể kết nối với chatbot. Vui lòng thử lại sau!')
    } finally {
      setDisabled(false)
    }
  }

  return (
    <form
      className='fixed bottom-3 flex justify-between items-center w-full max-w-lg p-1 px-3 pr-5 border border-solid border-[#e1e1e1] rounded-full'
      onSubmit={handleSend}>
      <InputComponent
        onChange={(e: React.ChangeEvent<HTMLInputElement>) => setSendInput(e.target.value)}
        placeholder='Tin nhắn Gemini'
        value={sendInput}
        className='min-w-96 text-black'
        disabled={disabled}
      />
      <button type='submit' disabled={disabled}>
        <Image src={Send} alt='send icon' width={20} height={20} />
      </button>
    </form>
  )
}

export default ChatbotFooter
