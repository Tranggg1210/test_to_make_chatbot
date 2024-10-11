import { IMessage, IMessageState } from "@/types/interface";
import { createSlice, PayloadAction } from "@reduxjs/toolkit";

const initialState: IMessageState = {
    listMessage: [],
};

const chatSlice = createSlice({
    name: "chat",
    initialState,
    reducers: {
        addMessage: (state, action: PayloadAction<IMessage>) => {
            state.listMessage.push(action.payload);
        },
    },
});

const chatReducer = chatSlice.reducer;
export const { addMessage} = chatSlice.actions;
export default chatReducer;
