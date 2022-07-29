import React from 'react';
import Typography from '@mui/material/Typography';
import { Link } from 'react-router-dom';

function Footer() {
    return (
        <footer className="sticky bottom-0">
            <Typography variant="body2" color="text.secondary" align="center">
                {'Copyright © '}
                <Link to="/" className='text-link'>
                    Hobby Keeping
                </Link>{' '}
                {new Date().getFullYear()}
                {'.'}
            </Typography>
        </footer >
    );
}

export default Footer;