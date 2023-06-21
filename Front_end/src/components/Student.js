import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function Student(props) {
  const params = useParams();
  const [student, setStudent] = useState({});
  const id = params.id;
  const navigate = useNavigate();
  const onSaveClick = (e) => {
    e.preventDefault();
    console.log(student);
    // Send data to the backend via POST
    fetch(`http://localhost:8080/student/save/${id}`, {
      method: `${id < 1 ? "POST" : "PUT"}`,
      mode: "cors",
      body: JSON.stringify(student), // body data type must match "Content-Type" header
      headers: {
        "Content-Type": "application/json; charset=ISO-8859-1",
      },
    })
      .then((response) => response.json())
      .then((data) => console.log(data))
      //.then((response) => navigate(`/students`))
      .catch((err) => console.log(err));
    navigate(`/students`);
    window.location.reload(false);
  };

  useEffect(() => {
    fetch(`http://localhost:8080/student/${id}`)
      .then((response) => response.json())
      .then((data) => setStudent(data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      <h1>{id < 0 ? "New Student" : `Student ${id}`}</h1>
      Id:{" "}
      <input
        type="number"
        value={student.id}
        onChange={(e) => setStudent({ ...student, id: e.target.value })}
      />
      <br />
      name:{" "}
      <input
        type="text"
        value={student.name}
        onChange={(e) => setStudent({ ...student, name: e.target.value })}
      />
      <br />
      dob:{" "}
      <input
        type="text"
        value={student.dob}
        onChange={(e) => setStudent({ ...student, dob: e.target.value })}
      />
      <br />
      major:{" "}
      <input
        type="text"
        value={student.major}
        onChange={(e) => setStudent({ ...student, major: e.target.value })}
      />
      <br />
      vaccinated:{" "}
      <input
        type="checkbox"
        checked={student.vaccinated}
        onChange={(e) =>
          setStudent({ ...student, vaccinated: e.target.checked })
        }
      />
      <br />
      <button onClick={(e) => onSaveClick(e)}>Save</button>
    </div>
  );
}

export default Student;
