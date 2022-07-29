import React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { Link as RouterLink } from 'react-router-dom';

function AboutYou({ onSubmitStep }) {
    const handleSubmit = (event) => {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const data = {
            email: formData.get('name'),
            password: formData.get('surname'),
        }
        onSubmitStep(data)
    };
    return (
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>

            <TextField
                margin="normal"
                required
                fullWidth
                id="name"
                label="Name"
                name="name"
                autoComplete="given-name"
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="surname"
                label="Surname"
                type="surname"
                id="surname"
                autoComplete="family-name"
            />

            <TextField
                margin="normal"
                required
                fullWidth
                id="username"
                label="Username"
                name="name"
                autoFocus
            />

            <TextField
                margin="normal"
                id="outlined-multiline-static"
                label="About You"
                multiline
                fullWidth
                rows={4}
            />

            <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
            >
                Next
            </Button>

        </Box>
    );
}

export default AboutYou;