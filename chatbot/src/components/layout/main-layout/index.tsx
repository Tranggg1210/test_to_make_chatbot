import './style.scss';
import { ReactNode } from "react";

function MainLayout({main}: {main: ReactNode}) {
  return <div className='main-layout'>
    {main}
  </div>;
}

export default MainLayout
