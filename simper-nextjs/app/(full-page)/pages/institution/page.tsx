'use client';
import React, { useContext, useRef, useState } from 'react';
import InstitutionService from '@/service/InstitutionService';
import { Institution } from '@/model/Institution';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import Link from 'next/link';
import { StyleClass } from 'primereact/styleclass';
import { NodeRef } from '@/types';
import { classNames } from 'primereact/utils';
import { Ripple } from 'primereact/ripple';
import { LayoutContext } from '@/layout/context/layoutcontext';

const InstitutionPage = () => {
    const [isHidden, setIsHidden] = useState(false);
    const { layoutConfig } = useContext(LayoutContext);
    const menuRef = useRef<HTMLElement | null>(null);
    const [institution, setInstitution] = useState<Institution>(new Institution);
    const service = new InstitutionService;

    const toggleMenuItemClick = () => {
        setIsHidden((prevState) => !prevState);
    };

    return (
        <div className="surface-0 flex justify-content-center">
            <div id="register" className="landing-wrapper overflow-hidden">
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
                                    <div className="p-fluid formgrid grid">
                                        <div className="field col-12 md:col-6">
                                            <label htmlFor="name">Name</label>
                                            <InputText id="name" type="text" />
                                        </div>
                                        <div className="field col-12 md:col-6">
                                            <label htmlFor="cnpj">CNPJ</label>
                                            <InputText id="cnpj" type="text" />
                                        </div>
                                        <div className="field col-12 md:col-6">
                                            <label htmlFor="email">E-mail</label>
                                            <InputText id="email" type="email" />
                                        </div>
                                        <div className="field col-12 md:col-6">
                                            <label htmlFor="emailResponsible">E-mail Person Responsible</label>
                                            <InputText id="emailResponsible" type="email" />
                                        </div>
                                        <div className="field col-12 md:col-6">
                                            <label htmlFor="domain">Domain</label>
                                            <InputText id="domain" type="text" />
                                        </div>
                                        <div className="field col-12 md:col-6">
                                            <label htmlFor="logo">Logo</label>
                                            <InputText id="logo" type="text" />
                                        </div>
                                    </div>
                                    <div className="flex flex-row md:flex-column justify-content-between w-full md:w-auto align-items-center md:align-items-end mt-5 md:mt-0">
                                        <Button label="Save" icon="pi pi-check" text onClick={() => service.save(institution)} />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div
                            className="col-12 mt-8 mb-8 p-2 md:p-8"
                            style={{
                                borderRadius: '20px',
                                background: 'linear-gradient(0deg, rgba(255, 255, 255, 0.6), rgba(255, 255, 255, 0.6)), radial-gradient(77.36% 256.97% at 77.36% 57.52%, #EFE1AF 0%, #C3DCFA 100%)'
                            }}
                        >
                            <div className="flex flex-column justify-content-center align-items-center text-center px-3 py-3 md:py-0">
                                <h3 className="text-gray-900 mb-2">Joséphine Miller</h3>
                                <span className="text-gray-600 text-2xl">Peak Interactive</span>
                                <p className="text-gray-900 sm:line-height-2 md:line-height-4 text-2xl mt-4" style={{ maxWidth: '800px' }}>
                                    “Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est
                                    laborum.”
                                </p>
                                <img src="/demo/images/landing/peak-logo.svg" className="mt-4" alt="Company logo" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default InstitutionPage;
