import type { Metadata } from 'next'
import localFont from 'next/font/local'
import './globals.scss'
import ProviderComponent from './ProviderComponent'

const geistSans = localFont({
  src: './fonts/GeistVF.woff',
  variable: '--font-geist-sans',
  weight: '100 900',
})
const geistMono = localFont({
  src: './fonts/GeistMonoVF.woff',
  variable: '--font-geist-mono',
  weight: '100 900',
})

export const metadata: Metadata = {
  title: 'chat bot',
  description: 'Chat bot này giúp người dùng tạo cơ sở dữ liệu miễn phí!',
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang='en'>
      <body className={`${geistSans.variable} ${geistMono.variable} antialiased`}>
        <ProviderComponent>
          {children}
        </ProviderComponent>
      </body>
    </html>
  )
}
