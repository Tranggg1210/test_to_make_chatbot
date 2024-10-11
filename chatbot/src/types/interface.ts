export interface inputProps {
  BRadius?: string;
  icon?: any;
  type?: string;
  BackGColor?: string;
  value?: string;
  onChange: (e: any) => void;
  onBlur?: any;
  placeholder: string;
  fontSize?: string;
  maxWidth?: string;
  width?: string;
  label?: string;
  name?: string;
  className?: string;
  disabled?: boolean;
  required?: boolean;
  error?: string;
  classNameOfLabel?: string;
  readOnly?: boolean;
  isTextarea?: boolean;
}

export interface IMessage {
  id: number;
  content: string;
  isUser?: boolean
}

export interface IMessageState {
  listMessage: IMessage[];
}
