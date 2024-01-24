
def LZ77_Encode(data):
    compressed_data = []
    window_size = 2048
    i = 0

    while i < len(data):   
        app_offset = 0
        app_length = 0

        for offset in range(1, min(i, window_size)+1):
            length = 0

            while (len(data) - i > length) and (data[i + length] == data[i - offset + length]):
                length += 1
            if length > app_length:
                app_length = length
                app_offset = offset

        if app_length > 2:
            offset, length = app_offset, app_length
            compressed_data.append((offset, length, data[i + length] if i + length < len(data) else ''))
            i += length
        else:
            compressed_data.append((0, 0, data[i]))
            
        i += 1

    return compressed_data
