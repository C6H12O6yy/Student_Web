import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
function Students(props) {
  const [students, setStudents] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const { id } = useParams();
  useEffect(() => {
    fetch("http://localhost:8080/students")
      .then((response) => response.json())
      .then((data) => setStudents(data))
      .catch((err) => console.log(err));
  }, []);
  const handleChange = (event) => {
    setSearchTerm(event.target.value);
  };
  const fillteredStudents = students.filter(
    (student) =>
      student.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      student.major.toLowerCase().includes(searchTerm.toLowerCase())
  );
  const deleleStudent = async (id) => {
    await axios.delete(`http://localhost:8080/student/delete/${id}`);
    fetch("http://localhost:8080/students")
      .then((response) => response.json())
      .then((data) => setStudents(data))
      .catch((err) => console.log(err));
  };
  return (
    <div class="container">
      <div class="row">
        <h1>List Students</h1>
        <input
          type="text"
          className="form-control"
          placeholder="Search student by name"
          value={searchTerm}
          onChange={handleChange}
        />
      </div>
      <table class="table table-striped table-bordered">
        <thead class="table-dark">
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Dob</th>
            <th>Major</th>
            <th>Vaccinated</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {fillteredStudents.map((student) => (
            <tr key={student.id}>
              <td> {student.id} </td>
              <td> {student.name} </td>
              <td> {student.dob} </td>
              <td> {student.major}</td>
              <td>
                <input type="checkbox" defaultChecked={student.vaccinated} />
              </td>
              <td>
                <Link to={`/student/${student.id}`} className="btn btn-primary">
                  View
                </Link>
                <button
                  className="btn btn-danger"
                  onClick={() => deleleStudent(student.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to={`/student/-1`} className="btn btn-primary">
        New Student
      </Link>
    </div>
  );
}

export default Students;
