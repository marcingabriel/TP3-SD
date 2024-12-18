import socket
import threading

def receive_messages(client_socket):
    while True:
        try:
            message = client_socket.recv(1024).decode("utf-8")
            if message:
                print(f'Recebido: {message}')
        except:
            print("Conexão encerrada.")
            break

def main():
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect(("127.0.0.1", 12345))

    thread = threading.Thread(target=receive_messages, args=(client,))
    thread.start()

    print("Conectado ao chat. Digite suas mensagens:")
    while True:
        message = input()
        if message.strip():  # Enviar apenas mensagens não vazias
            print(f'Enviado: "{message}"')
            client.send(message.encode("utf-8"))

if __name__ == "__main__":
    main()
