import React from 'react';
import Header from './components/Header';
import Footer from './components/Footer';
import Home2 from './pages/Home2'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// Vistas
import Home from './pages/Home';

export default function App() {
  return (
    <>
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/grid" element={<Home2/>} />
        </Routes>
        <Footer />
      </Router>
    </>
  );
}
