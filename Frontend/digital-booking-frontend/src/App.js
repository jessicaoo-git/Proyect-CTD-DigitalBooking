import { useState } from "react";
import AdminRoutes from "./routers/AdminRoutes";
import { userContext } from "./context/UserContext";



function App() {
  const [user, setUser] = useState(false)
  const [estado, setEstado] = useState(false)

  return (
    <div className="App">
    <userContext.Provider value={{
      user,
      setUser,
      estado,
      setEstado
    }}>
     <AdminRoutes/>
    </userContext.Provider>
    </div>
  );
}

export default App;
