import { Dropdown } from 'primereact/dropdown';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import React from 'react';

const InstitutionPage = () => {
    return (
        <div className="grid">
            <div className="col-12">
                <div className="card">
                    <h5>Hello there!</h5>
                    <p>Init with Simper, register your Educational Institution today!</p>
                </div>
            </div>

            <div className="col-12">
                <div className="card">
                    <h5>Register</h5>
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
                </div>
            </div>
        </div>
    );
};

export default InstitutionPage;
