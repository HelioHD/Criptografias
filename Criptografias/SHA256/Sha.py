#SHA256

from Crypto.Hash import SHA256

texto = input(Digite o texto )
objHash = SHA256.new()
objHash.update(texto.encode(utf-8))
print(objHash.hexdigest())
