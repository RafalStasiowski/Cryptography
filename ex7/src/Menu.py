from src.Blockchain import Blockchain


class Menu:
    def create(self):
        blockchain = Blockchain()

        key = 0
        while True:

            print('1. show blockchain')
            print('2. add transaction')
            print('3. add block')
            print('4. length of chain')
            print('5. exit')
            key = input()

            if key == '1':
                print(blockchain.chain)

            elif key == '2':
                sender = input('Insert sender')
                recipient = input('Insert recipient')
                amount = input('Insert amount')
                blockchain.add_transaction(sender, recipient, amount)
                print('Transaction successfully added')

            elif key == '3':
                proof = input("Enter proof")
                blockchain.add_block(proof)
                print('Block successfully added.')

            elif key == '4':
                print(len(blockchain.chain))

            elif key == '5':
                break
            else:
                print('Wrong input')


