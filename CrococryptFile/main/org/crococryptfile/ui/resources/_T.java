package org.crococryptfile.ui.resources;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public enum _T {
	CAPIDNListWindow_nokeys,
	CAPIDNListWindow_title,
	CAPI_DNnotfound,
	CrocoFilereader_already,
	CrocoFilereader_decrypting,
	CrocoFilewriter_encrypting,
	DecryptWindow_already,
	DecryptWindow_cancel,
	DecryptWindow_destination,
	DecryptWindow_failedgeneral,
	DecryptWindow_failedspecific,
	DecryptWindow_folderisfile,
	DecryptWindow_folderreadonly,
	DecryptWindow_invalid,
	DecryptWindow_nofile,
	DecryptWindow_success,
	DecryptWindow_text,
	DecryptWindow_title,
	DecryptWindow_unknownfile,
	DecryptWindow_wrongversion,
	Decrypt_Start,
	EncryptListChoose,
	EncryptListChooseMulti,
	EncryptWindow_EncryptedFile,
	EncryptWindow_Multidir,
	EncryptWindow_Multifile,
	EncryptWindow_MultifileMultidir,
	EncryptWindow_MultifileSingledir,
	EncryptWindow_Singledir,
	EncryptWindow_Singlefile,
	EncryptWindow_SinglefileMultidir,
	EncryptWindow_SinglefileSingledir,
	EncryptWindow_already,
	EncryptWindow_alreadydir,
	EncryptWindow_cancel,
	EncryptWindow_createdir,
	EncryptWindow_destination,
	EncryptWindow_failedgeneral,
	EncryptWindow_failedspecific,
	EncryptWindow_folderreadonly,
	EncryptWindow_multisources,
	EncryptWindow_success,
	EncryptWindow_title,
	FileSelection_dec4cloak,
	FileSelection_dec4enc,
	FileSelection_selectbutton,
	FileSelection_title,
	FileSelection_title4cloak,
	FilepathFailure,
	General_cancel,
	General_done,
	General_empty,
	General_none,
	General_open,
	General_password,
	General_quit,
	General_synchronize,
	JCEPolicyError_title,
	JCEPolicyError_text,
	Launcher_jcepolicyerr,
	Launcher_reallyquit,
	PasswordDecrypt_title,
	PasswordEncrypt_label,
	PasswordEncrypt_len,
	PasswordEncrypt_nomatch,
	PasswordEncrypt_retype,
	PasswordEncrypt_itcount,
	PasswordEncrypt_itcountdescr,
	PasswordEncrypt_title,
	Password_caps,
	Password_PBEinProgress,
	PGP_errorNokey,
	PGP_errorSeckeyfailed,
	PGP_nokeyfile,
	PGP_novalidprivkey,
	PGP_privkey_wrongfile,
	PGP_privkeypasstitle,
	PGP_selprivkey,
	PGP_selprivkeyTitle,
	PGP_selpubkey,
	PBKDF2Interstep_start,
	PBKDF2Interstep_found,
	PBKDF2Interstep_steplong,
	Suite_CAPI_RSAAES,
	Suite_PBE1_AES,
	Suite_PBE1_TWOFISH,
	Suite_PBE1_SERPENT,
	Suite_PBE1_CAMELLIA,
	Suite_PGP_AES,
	Suite_PBECLOAKED_AESTWO,
	Suite_PBECLOAKED1MB_AESTWO,

	;
	
	
	private static ResourceBundle rb;
	static{
		try {
			rb = ResourceBundle.getBundle(ResourceCenter.getTexts() + "text", ResourceCenter.getLocale());
		} catch (Exception e) {
			rb = ResourceBundle.getBundle(ResourceCenter.getTexts() + "text", Locale.ENGLISH);
		}
	}
	
	
	public String val(){
		return rb.getString(name());
	}
	
	public String msg(Object... obj){
		return MessageFormat.format(toString(), obj);
	}
	
	@Override
	public String toString() {
		return val();
	};
}
