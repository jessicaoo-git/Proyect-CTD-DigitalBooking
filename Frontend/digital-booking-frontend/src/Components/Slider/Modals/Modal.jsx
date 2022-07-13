import "./modal.css";

const Modal = ({ children, isOpen, closeModal }) => {
  return (
    <article className={`modalCarousel ${isOpen && "is-open"} `}>
      <div className="modal-container__corousel">
        <button className="modal-close" onClick={closeModal}>
          X
        </button>
        {children}
      </div>
    </article>
  );
};

export default Modal;
