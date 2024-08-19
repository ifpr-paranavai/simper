'use client';
import React, { ChangeEventHandler, useContext, useRef, useState } from 'react';
import { Button } from 'primereact/button';
import { LayoutContext } from '../../../layout/context/layoutcontext';
import { InputText } from 'primereact/inputtext';
import { classNames } from 'primereact/utils';
import UserAccountService from '@/app/service/UserAccountService';
import { Toast } from 'primereact/toast';
import { useRouter } from 'next/navigation';
import { UserPasswordResetRequest } from '@/app/model/UserPasswordResetRequest';

const ChangePasswordPage = () => {

    const { layoutConfig } = useContext(LayoutContext);

    const [ userPasswordResetRequest, setUserPasswordResetRequest ] = useState<UserPasswordResetRequest>(new UserPasswordResetRequest);
    const [ showPasswordReset, setShowPasswordReset ] = useState(true);

    const router = useRouter();
    const toast = useRef<Toast>(null);
    const containerClassName = classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden', { 'p-input-filled': layoutConfig.inputStyle === 'filled' });
    const service = new UserAccountService;

    const handleChange: ChangeEventHandler<HTMLInputElement> = (e) => {
        const { name, value } = e.target;
        setUserPasswordResetRequest(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const handleReset = () => {
        service.changePassword(userPasswordResetRequest)
        .then(res => {
            setShowPasswordReset(false);
            toast.current?.show({
                severity: 'success',
                summary: 'Successful',
                detail: 'Success',
                life: 3000
            });
        })
        .catch(err => {
            console.error(`Error on save: ${err}`);
            toast.current?.show({
                severity: 'error',
                summary: 'Error',
                detail: err.response ? JSON.stringify(err.response.data) : err.message,
                life: 3000
            });
        });
    }

    return (
        <div className={containerClassName}>
            <Toast ref={toast} />
            <div className="flex flex-column align-items-center justify-content-center">
                <img src={`/layout/images/logo-${layoutConfig.colorScheme === 'light' ? 'dark' : 'white'}.svg`} alt="Sakai logo" className="mb-5 w-6rem flex-shrink-0" />
                <div
                    style={{
                        borderRadius: '56px',
                        padding: '0.3rem',
                        background: 'linear-gradient(180deg, rgba(128, 0.4, 128, 0.4) 10%, rgba(33, 150, 243, 0) 30%)'
                    }}
                >
                    { showPasswordReset && 
                        <div className="w-full surface-card py-8 px-5 sm:px-8" style={{ borderRadius: '53px' }}>
                            <label htmlFor="email" className="block text-900 text-xl font-medium mb-2">
                                Email
                            </label>
                            <InputText 
                                id="email" 
                                name="email" 
                                type="text" 
                                placeholder="Email address"
                                value={userPasswordResetRequest.email}
                                onChange={handleChange} 
                                className="w-full md:w-30rem mb-5" 
                                style={{ padding: '1rem' }} 
                            />

                            <div className="flex align-items-center justify-content-between gap-5">
                                <Button label="Reset" severity="help" className="w-full p-3 teal-500 text-xl" onClick={handleReset}></Button>
                            </div>
                        </div>
                    }

                    { !showPasswordReset && 
                        <div className="w-full surface-card py-8 px-5 sm:px-8 flex flex-column align-items-center" style={{ borderRadius: '53px' }}>
                            <div className="flex justify-content-center align-items-center bg-purple-500 border-circle" style={{ height: '3.2rem', width: '3.2rem' }}>
                                <i className="pi pi-fw pi-check-circle text-2xl text-white"></i>
                            </div>
                            <h1 className="text-900 font-bold text-5xl mb-2">Password Reset Code Sent Successfully</h1>
                            <div className="text-600 mb-5">A password reset code has been successfully sent to your email. Please check your inbox and follow the instructions to reset your password.</div>
                            <Button icon="pi pi-arrow-left" label="Go to Login" text onClick={() => router.push('/auth/login')} />
                        </div>
                    }
                </div>
            </div>
        </div>
    );
};

export default ChangePasswordPage;
