import "./App.css";
import LoginHeader from "./components/LoginHeader";
import Footer from "./components/Footer";
import Students from "./components/Students";
import { Route, Routes } from "react-router-dom";
import Student from "./components/Student";

function App() {
  return (
    <div className="App">
      <LoginHeader></LoginHeader>
      <Routes>
        <Route path="/students" element={<Students />} />
        <Route path="/student/:id" element={<Student />} />
      </Routes>
      <Footer></Footer>
    </div>
  );
}

export default App;
