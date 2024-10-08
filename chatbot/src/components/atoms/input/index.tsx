import { inputProps } from "@/types/interface";
import "./style.scss";
import Image from "next/image";
const InputComponent = ({
  BRadius,
  icon,
  type = "text",
  BackGColor,
  onChange,
  onBlur,
  value,
  placeholder,
  fontSize,
  maxWidth,
  width,
  label,
  name,
  className = "",
  classNameOfLabel = "",
  disabled,
  required,
  readOnly,
  error,
  isTextarea,
}: inputProps) => {
  return (
    <>
      <div
        className={label ? "" : "input-component"}
        style={{
          borderRadius: BRadius,
          background: BackGColor,
          maxWidth: maxWidth,
        }}
      >
        {icon && <Image src={icon} alt="" />}
        <div>
          {label && (
            <div className={`mb-2.5 text-black font-bold ${classNameOfLabel}`}>
              {label} {required && <span className="text-[#ED3A3A]">*</span>}
            </div>
          )}
          {isTextarea ? (
            <textarea
              disabled={disabled}
              name={name}
              value={value}
              onChange={onChange}
              style={{
                background: BackGColor,
                fontSize: fontSize,
                width: width,
              }}
              rows={5}
              placeholder={placeholder}
              readOnly={readOnly}
              className={`${className} ${
                error && "border border-solid border-danger !outline-none"
              }`}
            />
          ) : (
            <input
              disabled={disabled}
              name={name}
              type={type}
              value={value}
              onChange={onChange}
              style={{
                background: BackGColor,
                fontSize: fontSize,
                width: width,
              }}
              onBlur={onBlur}
              placeholder={placeholder}
              readOnly={readOnly}
              className={`${className} ${
                error && "border border-solid border-danger !outline-none"
              }`}
            />
          )}
        </div>
      </div>
      {error && <div className="text-danger">{error}</div>}
    </>
  );
};
export default InputComponent;
