'use client';
import React, { ChangeEventHandler, useRef, useState } from 'react';
import InstitutionService from '@/app/service/InstitutionService';
import { Institution } from '@/app/model/Institution';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';

const InstitutionPage = () => {
    const toast = useRef<Toast>(null);

    const [institution, setInstitution] = useState<Institution>(new Institution);

    const service = new InstitutionService;

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
            toast.current?.show({
                severity: 'success',
                summary: 'Successful',
                detail: 'Institution updated',
                life: 3000
            });
        })
        .catch(err => {
            toast.current?.show({
                severity: 'error',
                summary: 'Error',
                detail: 'Error on updating',
                life: 3000
            });
        });
    }

    return (
        <div className="flex">
            <Toast ref={toast} />
            <div id="form" className="card">
                <h5 className="mb-2 text-900">Institution</h5>

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
                                disabled
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
                    <Button label="Update" icon="pi pi-check" text onClick={handleSave} />
                </div>
            </div>
        </div>
    );
};

export default InstitutionPage;
