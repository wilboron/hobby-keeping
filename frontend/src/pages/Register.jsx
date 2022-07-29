import {
    Container,
    Step, StepLabel, Stepper, Typography,
} from '@mui/material';
import { Box } from '@mui/system';
import React, { useEffect, useState } from 'react';
import AboutYou from '../components/Register/AboutYou';
import Login from '../components/Register/Login';
import Home from './Home';
//   import DadosEntrega from './DadosEntrega';
//   import DadosPessoais from './DadosPessoais';
//   import DadosUsuario from './DadosUsuario';

function Register() {
    const [etapaAtual, setEtapaAtual] = useState(0);
    const [collectedData, setData] = useState({});
    function nextStep() {
        setEtapaAtual(etapaAtual + 1);
    }
    function collectData(data) {
        setData({ ...collectedData, ...data });
        nextStep();
    }
    const formularios = [
        <AboutYou onSubmitStep={collectData} />,
        <Login onSubmitStep={collectData} />,
        <Home onSubmitStep={collectData} />,
    ];

    useEffect(() => {
        if (etapaAtual === formularios.length - 1) {
            console.log("SENDING TO SERVER" + collectedData);
        }
        console.log(collectedData);
    });

    return (
        <Container component="main" maxWidth="sm">
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                }}
            >
                <Stepper sx={{m: 1, mt: 5}} activeStep={etapaAtual}>
                    <Step><StepLabel>About You</StepLabel></Step>
                    <Step><StepLabel>Login</StepLabel></Step>
                    <Step><StepLabel>Finish</StepLabel></Step>
                </Stepper>
                {formularios[etapaAtual]
                    || <Typography color="red">Erro ao carregar Formulários</Typography>}
            </Box>
            </Container >
    );
}

export default Register;