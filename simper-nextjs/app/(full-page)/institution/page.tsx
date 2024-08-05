'use client';
import React, { ChangeEventHandler, useContext, useRef, useState } from 'react';
import InstitutionService from '@/app/service/InstitutionService';
import { Institution } from '@/app/model/Institution';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import Link from 'next/link';
import { StyleClass } from 'primereact/styleclass';
import { NodeRef } from '@/types';
import { classNames } from 'primereact/utils';
import { Ripple } from 'primereact/ripple';
import { LayoutContext } from '@/app/layout/context/layoutcontext';
import { Toast } from 'primereact/toast';

const InstitutionPage = () => {
    const toast = useRef<Toast>(null);
    const menuRef = useRef<HTMLElement | null>(null);

    const { layoutConfig } = useContext(LayoutContext);

    const [isHidden, setIsHidden] = useState(false);
    const [showForm, setShowForm] = useState(true);
    const [institution, setInstitution] = useState<Institution>(new Institution);

    const service = new InstitutionService;

    const toggleMenuItemClick = () => {
        setIsHidden((prevState) => !prevState);
    };

    const handleChange: ChangeEventHandler<HTMLInputElement> = (e) => {
        const { name, value } = e.target;
        setInstitution(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const handleSave = () => {
        service.save(institution)
        .then(res => {
            setShowForm(false);
            toast.current?.show({
                severity: 'success',
                summary: 'Successful',
                detail: 'Institution registered',
                life: 3000
            });
        })
        .catch(err => {
            toast.current?.show({
                severity: 'error',
                summary: 'Error',
                detail: 'Error on registering',
                life: 3000
            });
        });
    }

    return (
        <div className="surface-0 flex justify-content-center">
            <div id="register" className="landing-wrapper overflow-hidden">
                <Toast ref={toast} />
                <div className="py-4 px-4 mx-0 md:mx-6 lg:mx-8 lg:px-8 flex align-items-center justify-content-between relative lg:static">
                    <Link href="/" className="flex align-items-center">
                        <img src={`/layout/images/${layoutConfig.colorScheme === 'light' ? 'logo-dark' : 'logo-white'}.svg`} alt="Sakai Logo" height="50" className="mr-0 lg:mr-2" />
                        <span className="text-900 font-medium text-2xl line-height-3 mr-8">SAKAI</span>
                    </Link>
                    <StyleClass nodeRef={menuRef as NodeRef} selector="@next" enterClassName="hidden" leaveToClassName="hidden" hideOnOutsideClick>
                        <i ref={menuRef} className="pi pi-bars text-4xl cursor-pointer block lg:hidden text-700"></i>
                    </StyleClass>
                    <div className={classNames('align-items-center surface-0 flex-grow-1 justify-content-between hidden lg:flex absolute lg:static w-full left-0 px-6 lg:px-0 z-2', { hidden: isHidden })} style={{ top: '100%' }}>
                        <ul className="list-none p-0 m-0 flex lg:align-items-center select-none flex-column lg:flex-row cursor-pointer">
                            <li>
                                <a href="#home" onClick={toggleMenuItemClick} className="p-ripple flex m-0 md:ml-5 px-0 py-3 text-900 font-medium line-height-3">
                                    <span>Home</span>
                                    <Ripple />
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>

                { showForm &&
                    <div id="form" className="py-4 px-4 lg:px-8 mt-5 mx-0 lg:mx-8">
                        <div className="grid justify-content-center">
                            <div className="col-12 text-center mt-8 mb-4">
                                <h2 className="text-900 font-normal mb-2">Init with Simper, register your Educational Institution today!</h2>
                            </div>


                            <div className="col-12 md:col-12 lg:col-12 p-0 lg:pb-5 mt-4 lg:mt-0">
                                <div
                                    style={{
                                        padding: '2px',
                                        borderRadius: '10px',
                                        background: 'linear-gradient(90deg, rgba(253, 228, 165, 0.2), rgba(187, 199, 205, 0.2)), linear-gradient(180deg, rgba(253, 228, 165, 0.2), rgba(187, 199, 205, 0.2))'
                                    }}
                                >
                                    <div className="p-3 surface-card h-full" style={{ borderRadius: '8px' }}>
                                        <div
                                            className="flex align-items-center justify-content-center bg-yellow-200 mb-3"
                                            style={{
                                                width: '3.5rem',
                                                height: '3.5rem',
                                                borderRadius: '10px'
                                            }}
                                        >
                                            <i className="pi pi-fw pi-building text-2xl text-yellow-700"></i>
                                        </div>

                                        <h5 className="mb-2 text-900">Form</h5>

                                        <div className="p-fluid formgrid grid mt-5">
                                            <div className="field col-12 md:col-6">
                                                <span className="p-float-label">
                                                    <InputText
                                                        type="text"
                                                        id="name"
                                                        name="name"
                                                        value={institution.name}
                                                        onChange={handleChange}
                                                    />
                                                    <label htmlFor="inputtext">Name</label>
                                                </span>
                                            </div>
                                            <div className="field col-12 md:col-6">
                                                <span className="p-float-label">
                                                    <InputText
                                                        type="text"
                                                        id="cnpj"
                                                        name="cnpj"
                                                        value={institution.cnpj}
                                                        onChange={handleChange}
                                                    />
                                                    <label htmlFor="inputtext">CNPJ</label>
                                                </span>
                                            </div>
                                            <div className="field col-12 md:col-6 mt-3">
                                                <span className="p-float-label">
                                                    <InputText
                                                        type="email"
                                                        id="email"
                                                        name="email"
                                                        value={institution.email}
                                                        onChange={handleChange}
                                                    />
                                                    <label htmlFor="inputtext">E-mail</label>
                                                </span>
                                            </div>
                                            <div className="field col-12 md:col-6 mt-3">
                                                <span className="p-float-label">
                                                    <InputText
                                                        type="email"
                                                        id="emailResponsible"
                                                        name="emailResponsible"
                                                        value={institution.emailResponsible}
                                                        onChange={handleChange}
                                                    />
                                                    <label htmlFor="inputtext">E-mail Person Responsible</label>
                                                </span>
                                            </div>
                                            <div className="field col-12 md:col-6 mt-3">
                                                <span className="p-float-label">
                                                    <InputText
                                                        type="text"
                                                        id="domain"
                                                        name="domain"
                                                        value={institution.domain}
                                                        onChange={handleChange}
                                                    />
                                                    <label htmlFor="inputtext">Domain</label>
                                                </span>
                                            </div>
                                            <div className="field col-12 md:col-6 mt-3">
                                                <span className="p-float-label">
                                                    <InputText
                                                        type="text"
                                                        id="logo"
                                                        name="logo"
                                                        value={institution.logo}
                                                        onChange={handleChange}
                                                    />
                                                    <label htmlFor="inputtext">Logo</label>
                                                </span>
                                            </div>
                                        </div>

                                        <div className="flex flex-row md:flex-column justify-content-between w-full md:w-auto align-items-center md:align-items-end mt-5 md:mt-0">
                                            <Button label="Register" icon="pi pi-check" text onClick={handleSave} />
                                        </div>
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                }

                { !showForm &&
                    <div id="form" className="py-4 px-4 lg:px-8 mt-5 mx-0 lg:mx-8">
                        <div className="grid justify-content-center">
                            <div className="col-12 mt-8 mb-8 p-2 md:p-8"
                                style={{
                                    borderRadius: '20px',
                                    background: 'linear-gradient(0deg, rgba(255, 255, 255, 0.6), rgba(255, 255, 255, 0.6)), radial-gradient(77.36% 256.97% at 77.36% 57.52%, #EFE1AF 0%, #C3DCFA 100%)'
                                }}
                            >
                                <div className="flex flex-column justify-content-center align-items-center text-center px-3 py-3 md:py-0">
                                    <h3 className="text-gray-900 mb-2">Simper's Team</h3>
                                    <p className="text-gray-900 sm:line-height-2 md:line-height-4 text-2xl mt-4" style={{ maxWidth: '800px' }}>
                                        Thank you for registering your institution. An email with your login password will be sent to you shortly. Please check your inbox to log in.
                                    </p>
                                    <img src="/demo/images/landing/peak-logo.svg" className="mt-4" alt="Company logo" />
                                </div>
                            </div>
                        </div>
                    </div>
                }

                <div id="footer" className="py-4 px-4 mx-0 mt-8 lg:mx-8">
                    <div className="grid justify-content-between">
                        <div className="col-12 md:col-2" style={{ marginTop: '-1.5rem' }}>
                            <Link href="/" className="flex flex-wrap align-items-center justify-content-center md:justify-content-start md:mb-0 mb-3 cursor-pointer">
                                <img src={`/layout/images/${layoutConfig.colorScheme === 'light' ? 'logo-dark' : 'logo-white'}.svg`} alt="footer sections" width="50" height="50" className="mr-2" />
                                <span className="font-medium text-3xl text-900">SAKAI</span>
                            </Link>
                        </div>

                        <div className="col-12 md:col-10 lg:col-7">
                            <div className="grid text-center md:text-left">
                                <div className="col-12 md:col-3">
                                    <h4 className="font-medium text-2xl line-height-3 mb-3 text-900">Company</h4>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">About Us</a>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">News</a>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">Investor Relations</a>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">Careers</a>
                                    <a className="line-height-3 text-xl block cursor-pointer text-700">Media Kit</a>
                                </div>

                                <div className="col-12 md:col-3 mt-4 md:mt-0">
                                    <h4 className="font-medium text-2xl line-height-3 mb-3 text-900">Resources</h4>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">Get Started</a>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">Learn</a>
                                    <a className="line-height-3 text-xl block cursor-pointer text-700">Case Studies</a>
                                </div>

                                <div className="col-12 md:col-3 mt-4 md:mt-0">
                                    <h4 className="font-medium text-2xl line-height-3 mb-3 text-900">Community</h4>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">Discord</a>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">
                                        Events
                                        <img src="/demo/images/landing/new-badge.svg" className="ml-2" alt="badge" />
                                    </a>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">FAQ</a>
                                    <a className="line-height-3 text-xl block cursor-pointer text-700">Blog</a>
                                </div>

                                <div className="col-12 md:col-3 mt-4 md:mt-0">
                                    <h4 className="font-medium text-2xl line-height-3 mb-3 text-900">Legal</h4>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">Brand Policy</a>
                                    <a className="line-height-3 text-xl block cursor-pointer mb-2 text-700">Privacy Policy</a>
                                    <a className="line-height-3 text-xl block cursor-pointer text-700">Terms of Service</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default InstitutionPage;
