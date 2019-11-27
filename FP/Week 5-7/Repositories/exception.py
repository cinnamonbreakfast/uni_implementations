class RepositoryError(Exception):
    def __init__(self, message):
        self.__message = message

    def __str__(self):
        return self.__message

class ModelError(Exception):
    def __init__(self, message):
        self.__message = message

    def __str__(self):
        return self.__message

