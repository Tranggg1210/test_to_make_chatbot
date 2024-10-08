import Image from 'next/image'
import User from '@/assets/images/user.png';

function UserMessageItem({ message }: { message: string }) {
  return (
    <div className='flex items-center justify-end mb-4'>
      <div className='bg-blue-600 text-white py-2 px-4 rounded-lg rounded-tr-none'>{message}</div>
      <Image
        src={User}
        alt='User'
        className='w-8 h-8 mx-2 -mt-9'
        width={36}
        height={36}
      />
    </div>
  )
}

export default UserMessageItem
