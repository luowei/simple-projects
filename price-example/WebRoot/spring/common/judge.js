function subform(obj, pageNumber, productName, area) {
    if (undefined != pageNumber && (pageNumber != null || pageNumber != "")) {
        $("#pageNumber").val(pageNumber);
    }
    if (undefined != priceDate && (priceDate != null || priceDate != "")) {
        $("#hpriceDate").val(priceDate);
    }
    if (undefined != productName && (productName != null || productName != "")) {
        $("#hproductName").val(productName);
    }
    if (undefined != modelName && (modelName != null || modelName != "")) {
        $("#hmodelName").val(modelName);
    }
    $("#pageForm").submit();
}


