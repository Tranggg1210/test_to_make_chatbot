"use client";
import { Provider } from "react-redux";
import rootReducer from "@/reducers/redux-utils/rootReducer";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";


const ProviderComponent = ({ children }: { children: React.ReactNode }) => {

  return (
    <Provider store={rootReducer}>
      <ToastContainer
        position="top-right"
        autoClose={2000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
      {children}
    </Provider>
  );
};
export default ProviderComponent;
