import { Container } from '@mui/material';
import { Box } from '@mui/system';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Footer from './components/Footer';
import Header from './components/Header';
import Home from './pages/Home';
import Login from './pages/Login';
import NotFound from './pages/NotFound';
import Register from './pages/Register';

function App() {
  return (
    <Router>
      <Container maxWidth="lg">
        <Header />
        <Box sx={{height: 700, backgroundColor: '#cfdbff', marginTop: 4}}>
          <Routes>
            <Route exact path='/' element={<Home />} />
            <Route exact path='/signup' element={<Register />} />
            <Route exact path='/login' element={<Login />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </Box>
        <Footer />
      </Container>
    </Router>
  );
}

export default App;
