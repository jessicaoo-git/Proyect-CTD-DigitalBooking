import React, { useState } from "react";
import { useMediaQuery } from "react-responsive";
import DatePicker, { DateObject } from "react-multi-date-picker";

import gregorian_en_lowercase from "../../utils/calendar.locale";

import "./buscador-calendario.css";

const BuscadorCalendario = ({ onChange }) => {
  const isDesktop = useMediaQuery({ query: "(min-width: 992px)" });
  const isTablet = useMediaQuery({ query: "(min-width: 768px)" });

  const months = isTablet ? 2 : 1;

  const [shouldCloseCalendar, setShouldCloseCalendar] = useState(true);

  return (
    <DatePicker
      onClose={() => shouldCloseCalendar}
      locale={gregorian_en_lowercase}
      disableMonthPicker
      disableYearPicker
      onChange={onChange}
      numberOfMonths={months}
      className="home"
      arrow={false}
      type={"input-icon"}
      buttons={true}
      placeholder=" Check In - Check Out"
      range
      minDate={new DateObject().subtract(60, "days")}
      maxDate={new DateObject().add(364, "days")}
      mapDays={({ date, today }) => {
        let props = {};
        let result = date.toDays() - today.toDays();
        if (result < 0) props.disabled = true;
        return props;
      }}
   
    ></DatePicker>
  );
};

export default BuscadorCalendario;
