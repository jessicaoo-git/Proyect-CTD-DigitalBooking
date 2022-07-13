import "./login-error.css";

export default function LoginError({ children }) {
  return (
    <>
      <div className="contenedor">
        <img src="/assets/error.png" alt="Error" />
        <p className="message">{children}</p>
      </div>
    </>
  );
}
