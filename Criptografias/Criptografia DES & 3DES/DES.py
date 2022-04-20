from Crypto.Cipher import DES
import base64

def pad(texto):
    while ((len(texto) % 8) != 0):
        texto += " "
    return texto

texto = pad(input("Digite um texto: "))

senha = pad(input("Digite a senha: "))

cifraENC = DES.new(senha.encode("utf-8"), DES.MODE_ECB)
cifraDEC = DES.new(senha.encode("utf-8"), DES.MODE_ECB)

criptograma = str(base64.b64encode(cifraENC.encrypt(texto.encode("utf-8"))))
criptograma = criptograma[2: len(criptograma) - 1]
print(criptograma)

texto = cifraDEC.decrypt(base64.b64decode(criptograma))
texto = texto[2: len(texto) - 1]
print(texto)
