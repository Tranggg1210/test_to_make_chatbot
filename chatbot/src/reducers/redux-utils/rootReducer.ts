import { configureStore } from "@reduxjs/toolkit";
import chatReducer from "./chat";

const rootReducer = configureStore({
  reducer: {
    chatReducer
  },
});

export type RootState = ReturnType<typeof rootReducer.getState>;
export type AppDispatch = typeof rootReducer.dispatch;
export default rootReducer;
