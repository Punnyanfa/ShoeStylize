<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>ShoeStylize Mail</title>
    <style>
		body {
			display: flex;
			flex-direction: column;
			align-items: center;
		}

		.mail-title {
			display: flex;
			flex-direction: column;
			align-items: center;
			width: 60vw;
		}

        .table-container {
            width: 100%;
        }

        .table-node > table {
			width: 100%;
        }

		#logo {
			width: 150px;
			height: 150px;
		}
    </style>
</head>
<body>
<div class="mail-title">
    <img id="logo" src="https://nueqvbuobysxbgpelaih.supabase.co/storage/v1/object/public/shoes-explore/home/logo.png">
    <h1>ShoeStylize Mail</h1>
    <p>You have successfully purchased the following product(s).</p>
    <div class="table-container">
        <div class="table-node">
            <h3>Retro Shoe</h3>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Item</th>
                    <th>Value</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><b>Base price</b></td>
                    <td>$150</td>
                </tr>
                <tr>
                    <td>Extras</td>
                    <td>$150</td>
                </tr>
                <tr>
                    <td>Shipping fee</td>
                    <td>$2.5</td>
                </tr>
                <tr>
                    <td><b>Subtotal</b></td>
                    <td>$2.5</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <h4>Grand total: $340.0</h4>
    <div>
        <p>Enjoy your experience with your newly purchased shoe(s)!</p>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
