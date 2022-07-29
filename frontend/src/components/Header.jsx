import { AppBar, Button, IconButton, Toolbar, Typography } from '@mui/material';
import React from 'react';
import { Box } from '@mui/system';
import { Link } from 'react-router-dom';

function Header() {
    return (
        <AppBar position="static">
            <Toolbar variant="dense" >
                <Box sx={{ flexGrow: 1.2 }} />
                <Link to="/" className='text-link' >
                    <Typography variant="h6" color="inherit" component="div">
                        Hobby Keeping
                    </Typography>
                </Link>
                <Box sx={{ flexGrow: 1 }} />
                <Link to="/login" className='text-link' >
                    <Button color="inherit">
                        Sign in
                    </Button>
                </Link>
            </Toolbar>
        </AppBar >
    );
}

export default Header;